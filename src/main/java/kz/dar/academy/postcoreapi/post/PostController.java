package kz.dar.academy.postcoreapi.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        Post post = new Post(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "TEST",
                "initializing"
        );
        if (createPost(post).getStatusCode() != HttpStatus.OK)
            return new ResponseEntity<String>("Method createPost didn't work", HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            List<Post> allPosts = getAllPosts();
        } catch (Exception E) {
            return new ResponseEntity<String>("Method getAllPosts didn't work", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            Post temporaryPost = getPostById(post.getPostId());
        } catch (Exception E) {
            return new ResponseEntity<String>("Method getAllPosts didn't work", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (updatePostById(post.getPostId(), post).getStatusCode() != HttpStatus.OK)
            return new ResponseEntity<String>("Method updatePostById didn't work", HttpStatus.INTERNAL_SERVER_ERROR);
        if (deletePostById(post.getPostId()).getStatusCode() != HttpStatus.OK)
            return new ResponseEntity<String>("Method deletePostById didn't work", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>("Methods work correctly", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@Valid @RequestBody Post post) {
        postService.createPost(post);
        return new ResponseEntity<String>("Successfully created", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePostById(@PathVariable String id, @Valid @RequestBody Post post) {
        postService.updatePostById(id, post);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable String id) {
        postService.deletePostById(id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

}
