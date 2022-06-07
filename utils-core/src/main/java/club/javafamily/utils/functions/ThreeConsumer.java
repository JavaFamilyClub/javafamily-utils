package club.javafamily.utils.functions;

/**
 * @author Jack Li
 * @date 2021/7/26 4:21 下午
 * @description
 */
@FunctionalInterface
public interface ThreeConsumer<T, U, K> {

   /**
    * accept method.
    * @param t parma1
    * @param u parma2
    * @param k parma3
    */
   void accept(T t, U u, K k);
}
