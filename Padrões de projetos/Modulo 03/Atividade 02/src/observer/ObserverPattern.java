
package observer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação do padrão de projeto observer.
 * @author Vinícius Santos Lima
 */
public class ObserverPattern {
    private enum Events {
        OPENFILE,
        SAVEFILE,
        EDITFILE
    }
    
    interface EventListener {
        public abstract void update(Object... data);
    }
    
    class Editor {
        private EventManager event_manager;
        private BufferedReader br;
        private BufferedWriter bw;
        private File file;
        
        public Editor(EventManager event_manager) {
            this.event_manager = event_manager;
        }
        
        public void openFile(String file_path) {
            try {
                File file = new File(file_path);
                if (!file.exists())
                    file.createNewFile();
                    
                this.br = new BufferedReader(new FileReader(file));
                this.bw = new BufferedWriter(new FileWriter(file));
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.OPENFILE, file);
            }
        }
        
        public void saveFile() {
            try {
                if (this.br != null)
                    this.br.close();
                if (this.bw != null)
                    this.bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.SAVEFILE, file);
            }
        }
    }
    
    class TextEditor extends Editor {
        public void insertLine(int lineNumber, String text) {
            
        }
        
        public void removeLine(int lineNumber) {
            
        }
    }
    
    class EventManager {
               
        private class Subscription {
            public Events eventType;
            public EventListener listener;
            
            public Subscription(Events eventType, EventListener listener) {
                this.eventType = eventType;
                this.listener = listener;
            }
        }
        
        private Subscription[] listeners = new Subscription[0];
        
        public void subscribe(Events eventType, EventListener listener) {
            Subscription[] new_listeners = new Subscription[this.listeners.length + 1];
            Subscription new_sub = new Subscription(eventType, listener);
            
            for (int i = 0; i < this.listeners.length; i++) {
                if (this.listeners[i] == new_sub) 
                    throw new IllegalArgumentException("Subscription alredy exists");
                new_listeners[i] = this.listeners[i];
            }
            
            new_listeners[new_listeners.length - 1] = new_sub;
            this.listeners = new_listeners;
        }
        
        public void unsubscribe(Events eventType, EventListener listener) {
            if (this.listeners.length == 0)
                throw new ArrayIndexOutOfBoundsException("There are no subscriptions");
                
            Subscription[] new_listeners = new Subscription[this.listeners.length - 1];
            Subscription sel_sub = new Subscription(eventType, listener);
            
            boolean found_sub = false;
            for (int i = 0; i < new_listeners.length; i++) {
                if (found_sub || this.listeners[i] == sel_sub) {
                    new_listeners[i] = this.listeners[i+1];
                    if (!found_sub)
                        found_sub = true;
                }
                    
                new_listeners[i] = this.listeners[i];
            }
            
            if (!found_sub)
                throw new IllegalArgumentException("Subscription does not exists");
            
            this.listeners = new_listeners;
        }
        
        public void notfy(Events eventType, Object... data) {
            for (int i = 0; i < this.listeners.length; i++) {
                if (this.listeners[i].eventType == eventType) {
                    this.listeners[i].listener.update(data);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        
    }
    
}
