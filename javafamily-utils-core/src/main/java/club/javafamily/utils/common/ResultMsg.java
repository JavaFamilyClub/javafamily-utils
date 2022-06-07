package club.javafamily.utils.common;

import lombok.*;

/**
 * @author Jack Li
 * @date 2021/7/26 5:58 下午
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg<T> {

   private Integer code;
   private String msg;
   private T data;

   /**
    * 默认返回成功状态码，数据对象
    * @param data
    */
   public ResultMsg(T data) {
      this.code = ResultCode.SUCCESS.getCode();
      this.msg = ResultCode.SUCCESS.getMsg();
      this.data = data;
   }

   /**
    * 返回指定状态码，数据对象
     */
   public ResultMsg(ResultCode resultCode, T data) {
      this.code = resultCode.getCode();
      this.msg = resultCode.getMsg();
      this.data = data;
   }

}
