// 代码生成时间: 2025-09-23 13:53:01
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

// HashValueCalculator 是一个使用 JERSEY 框架的 RESTful Web 服务，用于计算字符串的哈希值。
@Path("/hash")
public class HashValueCalculator {

    // 计算哈希值的方法
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String calculateHash(@QueryParam("input") String input) {
        // 检查输入是否为空
        if (input == null || input.isEmpty()) {
            // 如果输入为空，返回错误信息
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        try {
            // 获取 MessageDigest 实例，这里以 SHA-256 为例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 将输入字符串转换为字节数组
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            // 返回计算出的哈希值
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果指定的哈希算法不可用，抛出异常
            throw new RuntimeException("Hash algorithm not available.", e);
        }
    }
}
