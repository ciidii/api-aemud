package org.aemudapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationListener<WebServerInitializedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        String appUrl = "http://localhost:" + port+"/index.html";

        log.info("Application démarrée sur : {}", appUrl);

        try {
            String os = System.getProperty("os.name").toLowerCase();
            log.info("Système d'exploitation détecté : {}", os);

            String command = "";
            if (os.contains("win")) {
                command = "cmd /c start \"\" \"" + appUrl + "\"";
                Runtime.getRuntime().exec(command);
            } else if (os.contains("mac")) {
                // Pour macOS : utiliser la commande 'open'
                command = "open \"" + appUrl + "\"";
                log.info("Commande d'ouverture du navigateur (macOS) : {}", command);
                Runtime.getRuntime().exec(command);
            } else if (os.contains("nix") || os.contains("nux")) {
                // Pour Linux : essayer 'xdg-open' (standard de Freedesktop)
                command = "xdg-open \"" + appUrl + "\"";
                log.info("Commande d'ouverture du navigateur (Linux) : {}", command);
                Runtime.getRuntime().exec(command);
            } else {
                log.warn("Système d'exploitation non reconnu pour l'ouverture automatique du navigateur.");
                log.warn("Veuillez ouvrir votre navigateur et accédez à {}", appUrl);
                return; // Quitter si OS non reconnu
            }

            log.info("Tentative d'ouverture du navigateur lancée.");

            // Optionnel : Ajouter un petit délai. Parfois, le processus a besoin d'un instant pour se lancer.
            // Attention : Thread.sleep bloque le thread principal de l'application, à utiliser avec prudence.
            // try { Thread.sleep(1000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }

        } catch (Exception e) {
            log.error("Erreur lors de l'exécution de la commande d'ouverture du navigateur : {}", e.getMessage(), e);
            log.warn("Veuillez ouvrir votre navigateur et accédez à {}", appUrl);
        }
    }
}