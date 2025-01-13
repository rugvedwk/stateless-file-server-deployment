import java.util.*;

// Stateful File Server Implementation
class StatefulFileServer {
    private Map<String, String> fileStorage;
    private Map<Integer, String> clientSessions;

    public StatefulFileServer() {
        fileStorage = new HashMap<>();
        clientSessions = new HashMap<>();
    }

    public int createSession(String clientName) {
        int sessionId = clientName.hashCode();
        clientSessions.put(sessionId, clientName);
        System.out.println("Session created for client: " + clientName + " with Session ID: " + sessionId);
        return sessionId;
    }

    public void uploadFile(int sessionId, String fileName, String content) {
        if (clientSessions.containsKey(sessionId)) {
            fileStorage.put(fileName, content);
            System.out.println("File uploaded successfully by client " + clientSessions.get(sessionId));
        } else {
            System.out.println("Invalid session. Upload failed.");
        }
    }

    public void downloadFile(int sessionId, String fileName) {
        if (clientSessions.containsKey(sessionId)) {
            String content = fileStorage.get(fileName);
            if (content != null) {
                System.out.println("File downloaded successfully: " + fileName);
                System.out.println("Content: " + content);
            } else {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Invalid session. Download failed.");
        }
    }

    public void closeSession(int sessionId) {
        if (clientSessions.containsKey(sessionId)) {
            System.out.println("Session closed for client: " + clientSessions.get(sessionId));
            clientSessions.remove(sessionId);
        } else {
            System.out.println("Invalid session ID.");
        }
    }
}

// Stateless File Server Implementation
class StatelessFileServer {
    private Map<String, String> fileStorage;

    public StatelessFileServer() {
        fileStorage = new HashMap<>();
    }

    public void uploadFile(String fileName, String content) {
        fileStorage.put(fileName, content);
        System.out.println("File uploaded successfully: " + fileName);
    }

    public void downloadFile(String fileName) {
        String content = fileStorage.get(fileName);
        if (content != null) {
            System.out.println("File downloaded successfully: " + fileName);
            System.out.println("Content: " + content);
        } else {
            System.out.println("File not found.");
        }
    }
}

public class FileServerExample {
    public static void main(String[] args) {
        System.out.println("--- Stateful File Server ---");
        StatefulFileServer statefulServer = new StatefulFileServer();
        int sessionId = statefulServer.createSession("Client1");
        statefulServer.uploadFile(sessionId, "example.txt", "This is a sample file.");
        statefulServer.downloadFile(sessionId, "example.txt");
        statefulServer.closeSession(sessionId);

        System.out.println("\n--- Stateless File Server ---");
        StatelessFileServer statelessServer = new StatelessFileServer();
        statelessServer.uploadFile("example.txt", "This is a sample file.");
        statelessServer.downloadFile("example.txt");
    }
}
