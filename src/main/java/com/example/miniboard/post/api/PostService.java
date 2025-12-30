package com.example.miniboard.post.api;

import com.example.miniboard.global.error.NotFoundException;
import com.example.miniboard.post.domain.Post;
import com.example.miniboard.post.domain.PostRepository;
import com.example.miniboard.post.dto.PostCreateRequest;
import com.example.miniboard.post.dto.PostResponse;
import com.example.miniboard.post.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse create(PostCreateRequest req) {
        Post post = new Post(req.title(), req.content(), req.author());
        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public PostResponse getOne(Long id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> getList(String q, Pageable pageable) {
        if (q == null || q.isBlank()) {
            return postRepository.findAllByDeletedFalse(pageable).map(PostResponse::from);
        }
        return postRepository.findAllByDeletedFalseAndTitleContainingIgnoreCase(q, pageable).map(PostResponse::from);
    }

    @Transactional
    public PostResponse update(Long id, PostUpdateRequest req) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        post.update(req.title(), req.content());
        return PostResponse.from(post);
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        post.softDelete();
    }
}

