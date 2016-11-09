package org.helianto.mailer.service

import com.iservport.message.domain.Message
import org.springframework.stereotype.Service

@Service
class EchoService {

  def handle(message: Message) = {
    val received =
      s"""
         |On-line:  [ link ${message.getDefaults.seeOnline} ${message.getDefaults.apiHome}/redirect/${message.getTemplate}/${message.getId} ]
         |
         |id:       ${message.getId}
         |
         |Sent by:  ${message.getSender.contactName} <${message.getSender.contactEmail}>
         |Entity:   ${message.getSender.entityName}
         |Address:  ${message.getSender.address}
         |
         |To:       ${message.getRecipient.contactName} <${message.getRecipient.contactEmail}>
         |Entity:   ${message.getRecipient.entityName}
         |Address:  ${message.getRecipient.address}
         |
         |Subject:  ${message.getMessageData.subject} <${message.getMessageData.title}>
         |
         |${message.getDefaults.greeting}, ${message.getRecipient.contactName}
         |
         |${message.getMessageData.procedure}
         |
         |[ button -> ${message.getMessageData.callToAction} ]
         |
         |${message.getMessageData.fallBack}
         |
         |[ link -> ${message.getDefaults.apiHome}${message.getServicePath}/${message.getId} ]
         |
         |----
         |
         |${message.getDefaults.sentByText} ${message.getSender.contactEmail}
         |${message.getDefaults.disclaimer}
         |${message.getDefaults.ensure}
         |${message.getDefaults.unsubscribeText}, [ link ${message.getDefaults.unsubscribeCaption} -> ${message.getDefaults.apiHome}/${message.getDefaults.unsubscribeService} ]
         |${message.getDefaults.copyright}
         |
       """.stripMargin
    println(received)
    received
  }

}
