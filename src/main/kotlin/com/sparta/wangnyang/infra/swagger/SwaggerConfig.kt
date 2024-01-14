package com.sparta.wangnyang.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI():OpenAPI= OpenAPI()
            .components(Components())
            .info(
                    Info()
                            .title("WangNyang API")
                            .description("WangNyan API Schema \n 고양이와 강아지를 좋아하는 사람들의 커뮤니티")
                            .version("1.0.0")
            )

}