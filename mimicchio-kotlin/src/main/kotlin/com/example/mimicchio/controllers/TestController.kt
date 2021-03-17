package com.example.mimicchio.controllers

import com.example.mimicchio.entities.Mimicchio
import com.example.mimicchio.security.User
import com.example.mimicchio.utils.Log
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Log
class TestController {

    @GetMapping(value = ["ktn/", "ktn"])
    @PreAuthorize("hasAnyAuthority('hello','ORGANIZATIONS_LIST_IN_ORGANIZATION')")
    fun findAll(): ResponseEntity<Mimicchio> {

        val user: User = SecurityContextHolder.getContext().authentication.principal as User

        return ResponseEntity.ok(Mimicchio(
                id = user.id,
                name = user.username
        ))

    }

}
