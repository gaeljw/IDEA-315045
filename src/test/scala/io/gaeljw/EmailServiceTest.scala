package io.gaeljw

import org.apache.commons.mail.EmailException
import org.jvnet.mock_javamail.Mailbox
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.nio.file.Files
import javax.mail.internet.{MimeBodyPart, MimeMultipart}

// Note: The "fake" Mailbox is automatically enabled as soon as the dependency is present in the classpath
class EmailServiceTest extends AnyWordSpec with Matchers with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    // clear Mock JavaMail box
    Mailbox.clearAll()
  }

  private val fakeConfig = SmtpConfig(host = "not-existing-smtp.mycompany.net", port = 1025)

  "EmailService" should {

    "Send an email" in {

      val emailService = new EmailService(fakeConfig)
        
      val result = emailService.sendEmail()

      result.failed.foreach(println)

      result.isSuccess shouldEqual true

      // Assertions
      val mailbox = Mailbox.get("me@me.com")
      mailbox.getNewMessageCount shouldEqual 1

      val receivedEmail = mailbox.get(0)
      receivedEmail.getFrom.length shouldEqual 1
      receivedEmail.getFrom.head.toString shouldEqual "noreply@me.com"
      receivedEmail.getSubject shouldEqual "Subject"
    }

  }

}
