package gets

import s8scala.api.{ BaseStreamHandler, RequestJson }

trait Base extends BaseStreamHandler {
  import s8scala.api.ResponseJsonConverters._

  override protected def handle(requestJson: RequestJson): Either[Invalid, Valid] = {
    Right("{}".toOk)
  }

}
