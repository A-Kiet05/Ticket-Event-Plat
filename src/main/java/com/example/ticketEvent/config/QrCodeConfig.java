

@Configuration
public class QrCodeConfig {
    
    @Bean
    public QrCodeWriter qrCodeWriter() {
        return new QrCodeWriter();
    }
}