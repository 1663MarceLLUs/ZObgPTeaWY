// 代码生成时间: 2025-10-04 20:52:49
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;

// FirewallRule 类表示一个防火墙规则
class FirewallRule {
    private String ruleId;
    private String action;
    private String protocol;
    private String source;
    private String destination;
    private int port;

    // 构造函数、getter和setter省略
}

// FirewallRuleService 类提供防火墙规则管理的业务逻辑
class FirewallRuleService {
    private List<FirewallRule> rules = new ArrayList<>();

    public List<FirewallRule> getRules() {
        return rules;
    }

    public void addRule(FirewallRule rule) {
        if (rule != null) {
            rules.add(rule);
        } else {
            throw new IllegalArgumentException("Rule cannot be null");
        }
    }

    // 其他业务逻辑方法省略
}

// FirewallRuleResource 类提供防火墙规则管理的REST API
@Path("/firewall")
public class FirewallRuleResource {
    private final FirewallRuleService service = new FirewallRuleService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FirewallRule> getRules() {
        return service.getRules();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addRule(FirewallRule rule) {
        try {
            service.addRule(rule);
        } catch (IllegalArgumentException e) {
            // 错误处理逻辑，例如返回错误信息
            throw new RuntimeException(e.getMessage());
        }
    }

    // 其他API方法省略
}

// FirewallRuleApplication 类配置Jersey应用程序
public class FirewallRuleApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(FirewallRuleResource.class);
        s.add(FreemarkerMvcFeature.class);
        return s;
    }
}
