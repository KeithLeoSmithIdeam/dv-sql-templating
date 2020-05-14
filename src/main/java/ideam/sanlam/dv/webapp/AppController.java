package ideam.sanlam.dv.webapp;

import ideam.sanlam.dv.infrastructure.config.WebConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    private final PostService postService;

    @Autowired
    private WebConfigProperties conf;

    public AppController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/app"})
    public String getUserListing(Model model) {
        model.addAttribute("baseHref",conf.getBaseHrefStaticFiles());
        model.addAttribute("posts", this.postService.getPosts());
        return "home";
    }

}