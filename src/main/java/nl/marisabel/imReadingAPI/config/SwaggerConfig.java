/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


 @Bean
 public OpenAPI customOpenAPI(
         @Value("${apiTitle}") String apiTitle,
         @Value("${apiDescription}") String apiDescription,
         @Value("${apiVersion}") String apiVersion,
         @Value("${apiContactName}") String apiContactName,
         @Value("${apiContactEmail}") String apiContactEmail,
         @Value("${apiContactUrl}") String apiContactUrl

 ) {
  return new OpenAPI()
          .components(new Components())
          .info(new Info()
                  .title(apiTitle)
                  .description(apiDescription)
                  .version(apiVersion)
                  .contact(new Contact().name(apiContactName).email(apiContactEmail).url(apiContactUrl))
          );
 }
}
