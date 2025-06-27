package listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[INFO] Aplicação liberNexus iniciada.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[INFO] Encerrando aplicação liberNexus...");
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("[INFO] Thread mysql-cj-abandoned-connection-cleanup encerrada com sucesso.");
        } catch (Exception e) {
            System.err.println("[ERRO] Falha ao encerrar thread de limpeza do MySQL:");
            e.printStackTrace();
        }
    }
}