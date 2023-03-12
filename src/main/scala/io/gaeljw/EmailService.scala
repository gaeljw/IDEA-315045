package io.gaeljw

import org.apache.commons.mail.{Email, EmailAttachment, EmailException, HtmlEmail}
import scala.util.Try

import java.nio.charset.StandardCharsets

case class SmtpConfig(host: String, port: Int)

class EmailService(smtpConfig: SmtpConfig) {

  private val smtpHost = smtpConfig.host
  private val smtpPort = smtpConfig.port

  def sendEmail(): Try[Unit] = {
    Try {
        val email = new HtmlEmail()
        email.setHostName(smtpHost)
        email.setSmtpPort(smtpPort)
        email.setFrom("noreply@me.com")
        email.addTo("me@me.com")
        email.setSubject("Subject")
        email.setHtmlMsg("<html></html>")
        email.setCharset(StandardCharsets.UTF_8.toString)

        email.send()
        ()
      }
  }

}
