package club.javafamily.utils.common;

/**
 * @author Jack Li
 * @date 2021/7/26 5:59 下午
 * @description transform message exception.
 */
public class MessageException extends RuntimeException {

   private ResultCode resultCode = ResultCode.SYSTEM_ERROR;

   public MessageException(String message) {
      this(ResultCode.SYSTEM_ERROR, message);
   }

   public MessageException(ResultCode resultCode, String message) {
      super(message);
      this.resultCode = resultCode;
   }

   public MessageException(String message, Throwable cause) {
      this(ResultCode.SYSTEM_ERROR, message, cause);
   }

   public MessageException(ResultCode resultCode,
                           String message,
                           Throwable cause)
   {
      super(message, cause);
      this.resultCode = resultCode;
   }

   public MessageException(Throwable cause) {
      super(cause);
   }

   public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public ResultCode getResultCode() {
      return resultCode;
   }

   public void setResultCode(ResultCode resultCode) {
      this.resultCode = resultCode;
   }
}
