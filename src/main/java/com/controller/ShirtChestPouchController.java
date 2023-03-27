package com.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/chestPouch/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "衬衫胸袋")
public class ShirtChestPouchController {

}
