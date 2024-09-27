package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    public class TaskControllerTest {    
        private Task task;
    
        @BeforeEach
        public void beforeEach() {
            this.task = Instancio.of(Task.class)
                    .ignore(Select.field(Task::getId))
                    .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                    .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                    .create();
        }
    
        @Test
        public void testShow() throws Exception {
            String title = task.getTitle();
            String description = task.getDescription();
            taskRepository.save(task);
            var request = get("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andExpect(status().isOk());
    
            task = taskRepository.findById(task.getId()).get();
            assertThat(task.getTitle()).isEqualTo(title);
            assertThat(task.getDescription()).isEqualTo(description);
        }
    
        @Test
        public void testCreate() throws Exception {
            taskRepository.save(task);
    
            String title = task.getTitle();
            String description = task.getDescription();
    
            var request = post("/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(task));
    
            mockMvc.perform(request)
                    .andExpect(status().isCreated());
    
            task = taskRepository.findById(task.getId()).get();
            assertThat(task.getTitle()).isEqualTo(title);
            assertThat(task.getDescription()).isEqualTo(description);
        }
    
        @Test
        public void testUpdate() throws Exception {
            taskRepository.save(task);
            HashMap<String, String> data = new HashMap<>();
            data.put("title", "test");
            data.put("description", "test description");

            var request = put("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(data));
            mockMvc.perform(request)
                    .andExpect(status().isOk());
    
            task = taskRepository.findById(task.getId()).get();
            assertThat(task.getTitle()).isEqualTo("test");
            assertThat(task.getDescription()).isEqualTo("test description");
        }
    
        @Test
        public void testDelete() throws Exception {
            taskRepository.save(task);
        
            var request = delete("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON);
        
            mockMvc.perform(request)
                    .andExpect(status().isNoContent());
        
            boolean taskExists = taskRepository.findById(task.getId()).isPresent();
            assertThat(taskExists).isFalse();
        }
    }
    // END
}
