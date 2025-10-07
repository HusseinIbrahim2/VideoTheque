package springbt.videotheque.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springbt.videotheque.model.Actor;
import springbt.videotheque.service.ActorService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("controller_actor")
@WebMvcTest(controllers = ActorController.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService service;

    @DisplayName("Actor list")
    @Test
    public void getAllActorsTest() throws Exception {
        // GIVEN
        when(service.findAll("")).thenReturn(Collections.emptyList());

        // WHEN / THEN
        mockMvc.perform(get("/actors"))
                .andExpect(status().isOk());
    }

    @DisplayName("Get one actor by id")
    @Test
    public void getActorByIdTest() throws Exception {
        // GIVEN
        long idActor = 1L;
        Optional<Actor> actor = Optional.of(new Actor("Keanu Reeves","Bio Keanu"));
        actor.get().setId(idActor);
        when(service.findById(idActor)).thenReturn(actor);

        // WHEN / THEN
        mockMvc.perform(get("/actors/{id}", idActor))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Keanu Reeves"))
                .andExpect(jsonPath("$.bio").value("Bio Keanu"));
    }

    @DisplayName("Add actor")
    @Test
    public void addActorTest() throws Exception {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();
        Actor in = new Actor("Keanu Reeves","Bio Keanu");
        String json = mapper.writeValueAsString(in);

        Actor out = new Actor("Keanu Reeves","Bio Keanu");
        out.setId(1L);
        when(service.insert(isA(Actor.class))).thenReturn(out);

        // WHEN / THEN
        mockMvc.perform(post("/actors")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Keanu Reeves"))
                .andExpect(jsonPath("$.bio").value("Bio Keanu"));
    }

    @DisplayName("Update actor")
    @Test
    public void updateActorTest() throws Exception {
        // GIVEN
        long idActor = 1L;
        Actor actor = new Actor("Keanu Reeves","Updated Bio");
        actor.setId(idActor);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(actor);

        when(service.updateById(isA(Long.class), isA(Actor.class))).thenReturn(actor);

        // WHEN / THEN
        mockMvc.perform(put("/actors/{id}", idActor)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Keanu Reeves"))
                .andExpect(jsonPath("$.bio").value("Updated Bio"));
    }

    @DisplayName("Delete actor")
    @Test
    public void deleteActorTest() throws Exception {
        // GIVEN
        long idActor = 1L;
        Actor actor = new Actor("Keanu Reeves","Bio Keanu");
        actor.setId(idActor);
        when(service.findById(idActor)).thenReturn(Optional.of(actor));
        doNothing().when(service).deleteById(idActor);

        // WHEN / THEN
        mockMvc.perform(delete("/actors/{id}", idActor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
