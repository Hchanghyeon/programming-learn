package com.lock.namedlock.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.lock.namedlock.common.repository.LockRepository;
import com.lock.namedlock.post.dto.PostCreateRequest;
import com.lock.namedlock.post.dto.PostResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLockService {

    private final PostService postService;
    private final LockRepository lockRepository;

    @Transactional
    public PostResponse createPost(final PostCreateRequest request) {
        Long memberId = request.getMemberId();

        lockRepository.lockAcquired(String.valueOf(memberId));
        log.info("Lock Acquired, memberId : {}", memberId);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                lockRepository.lockRelease(String.valueOf(memberId));
                log.info("Lock Released, memberId : {}", memberId);
            }
        });

        return postService.createPost(request);
    }
}
