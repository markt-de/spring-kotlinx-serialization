package de.classyfi.boot.autoconfigure.spring.kotlinx.serialization

import de.classyfi.libs.spring.kotlinx.serialization.codec.KotlinxSerializationEncoder
import kotlinx.serialization.SerializationStrategy
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.REACTIVE
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration
import org.springframework.boot.web.codec.CodecCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Bernhard Frauendienst <bernhard.frauendienst@markt.de>
 */
@Configuration
@ConditionalOnClass(SerializationStrategy::class)
@AutoConfigureBefore(WebFluxAutoConfiguration::class)
class SpringKotlinxSerializationAutoConfiguration {

  @Configuration
  @ConditionalOnClass(KotlinxSerializationEncoder::class)
  @ConditionalOnWebApplication(type = REACTIVE)
  class KotlinxSeralizationEncoderConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun kotlinxSerializationEncoder(): KotlinxSerializationEncoder = KotlinxSerializationEncoder.defaultJsonEncoder()

    @Bean
    fun kotlinxSerializationCodecCustomizer(encoder: KotlinxSerializationEncoder) = CodecCustomizer {
      it.customCodecs().encoder(encoder)
    }
  }

}