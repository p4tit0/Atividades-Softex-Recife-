
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
    
    class OpenListener implements EventListener{
        @Override
        public void update(Object... data) {
            if (data.length <= 0) 
                throw new IllegalArgumentException("Nome do arquivo não foi fornecido.");
            System.out.printf("O arquivo \"%s\" foi aberto com sucesso!\n", (String) data[0]);
        }
    }
    
    class SaveListener implements EventListener{
        @Override
        public void update(Object... data) {
            if (data.length <= 0) 
                throw new IllegalArgumentException("Nome do arquivo não foi fornecido.");
            System.out.printf("O arquivo \"%s\" foi salvo com sucesso!\n", (String) data[0]);
        }
    }
   
    class EditListener implements EventListener{
        @Override
        public void update(Object... data) {
            if (data.length <= 0) 
                throw new IllegalArgumentException("Nome do arquivo não foi fornecido.");
            System.out.printf("As alterações no arquivo \"%s\" fora feitas com sucesso!\n", (String) data[0]);
        }
    }
    
    
    class Editor {
        protected EventManager event_manager;
        protected BufferedReader br;
        protected BufferedWriter bw;
        protected File file;
        
        public Editor(EventManager event_manager) {
            this.event_manager = event_manager;
        }
        
        public void openFile(String file_path) {
            try {
                this.file = new File(file_path);
                
                if (!file.exists())
                    file.createNewFile();
                    
                this.br = new BufferedReader(new FileReader(file));
                this.bw = new BufferedWriter(new FileWriter(file));
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.OPENFILE, file.getAbsolutePath());
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
                event_manager.notfy(Events.SAVEFILE, file.getAbsolutePath());
            }
        }
    }
    
    class TextEditor extends Editor {
        
        public TextEditor(EventManager event_manager) {
            super(event_manager);
        }
        
        public void insertLine(int lineNumber, String text) {
            String line;
            int count = 0;
            boolean found = false;
            try {              
                File temp = File.createTempFile(this.file.getName(), ".tmp");
                BufferedWriter tempbw = new BufferedWriter(new FileWriter(temp));
                
                while ((line = this.br.readLine()) != null) {
                    if (count == lineNumber) {
                        tempbw.write(text);
                        tempbw.newLine();
                        found = true;
                    }
                    tempbw.write(line);
                    count++;
                }
                if (!found)
                    tempbw.write(text);
                
                tempbw.close();
                BufferedReader tempbr = new BufferedReader(new FileReader(temp));
                while ((line = tempbr.readLine()) != null) {
                    this.bw.write(line);
                    this.bw.newLine();
                }
                
                tempbr.close();
                temp.delete();
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.EDITFILE, file.getAbsolutePath());
            }
            
            
            
        }
        
        public void removeLine(int lineNumber) {
            String line;
            int count = 0;
            try {
                File temp = File.createTempFile(this.file.getName(), ".tmp");
                BufferedWriter tempbw = new BufferedWriter(new FileWriter(temp));
                
                while ((line = this.br.readLine()) != null) {
                    if (count == lineNumber) 
                        continue;
                    tempbw.write(line);
                    count++;
                }
                
                tempbw.close();
                BufferedReader tempbr = new BufferedReader(new FileReader(temp));
                while ((line = tempbr.readLine()) != null) {
                    this.bw.write(line);
                    this.bw.newLine();
                }
                
                tempbr.close();
                temp.delete();
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.EDITFILE, file.getAbsolutePath());
            }
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
    
    public void run() {
        OpenListener ol = new OpenListener();
        SaveListener sl = new SaveListener();
        EditListener el = new EditListener();
        
        EventManager em = new EventManager();
        
        em.subscribe(Events.OPENFILE, ol);
        em.subscribe(Events.SAVEFILE, sl);
        em.subscribe(Events.EDITFILE, el);
        
        TextEditor te = new TextEditor(em);
        
        te.openFile("test.txt");
        te.insertLine(0, "line 1");
        te.insertLine(1, "line 3");
        te.saveFile();
        te.openFile("test.txt");
        te.insertLine(1, "line 2");
        
        te.saveFile();
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        ObserverPattern op = new ObserverPattern();
        op.run();
    }
    
}
