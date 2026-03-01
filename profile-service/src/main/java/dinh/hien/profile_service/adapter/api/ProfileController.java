package dinh.hien.profile_service.adapter.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    @GetMapping("/test")
    public String test() {
        return "test ok";
    }
}
