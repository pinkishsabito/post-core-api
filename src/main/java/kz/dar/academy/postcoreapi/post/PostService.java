package kz.dar.academy.postcoreapi.post;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private static final HashMap<String, Post> employeeMap = new HashMap<>();

    static {
        Post post1 = new Post(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "Laptop",
                "finished"
        );
        Post post2 = new Post(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "PC",
                "initializing"
        );
        Post post3 = new Post(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "Mouse",
                "finished"
        );
        employeeMap.put(post1.getPostId(), post1);
        employeeMap.put(post2.getPostId(), post2);
        employeeMap.put(post3.getPostId(), post3);
    }

    void createPost(Post post) {
        employeeMap.put(post.getPostId(), post);
    }

    List<Post> getAllPosts() {
        return new ArrayList<>(employeeMap.values());
    }

    Post getPostById(String id) {
        return employeeMap.get(id);
    }

    void updatePostById(String id, Post post) {
        post.setPostId(id);
        employeeMap.put(id, post);
    }

    void deletePostById(String id) {
        employeeMap.remove(id);
    }
}
