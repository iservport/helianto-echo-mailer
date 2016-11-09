package org.helianto.mailer.controller

import com.iservport.message.domain.Message
import org.helianto.mailer.service.EchoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping(Array("/sendConfirmation"))
class EchoController(service: EchoService) {

  @PostMapping
  @ResponseBody
  def handle(@RequestBody message: Message) = service.handle(message)

}
