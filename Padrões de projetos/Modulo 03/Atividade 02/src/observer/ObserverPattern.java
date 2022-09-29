
package observer;

import java.io.*;
import java.util.Arrays;
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
            System.out.printf("As alterações no arquivo \"%s\" foram feitas com sucesso!\n", (String) data[0]);
        }
    }
    
    
    static class Editor {
        protected EventManager event_manager;
        protected File file;
        protected File temp;
        
        public Editor(EventManager event_manager) {
            this.event_manager = event_manager;
        }
        
        public void openFile(String file_path) {
            try {
                this.file = new File(file_path);
                
                if (!file.exists())
                    file.createNewFile();
                
                this.temp = File.createTempFile(this.file.getName(), ".tmp");

                BufferedReader br = new BufferedReader(new FileReader(this.file));
                BufferedWriter tempbw = new BufferedWriter(new FileWriter(this.temp));
                String line;
                while ((line = br.readLine()) != null) {
                    tempbw.write(line);
                    tempbw.newLine();
                }
                tempbw.close();
                br.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.OPENFILE, file.getAbsolutePath());
            }
        }
        
        public void saveFile() {
            try {
                String line;
                BufferedReader tempbr = new BufferedReader(new FileReader(this.temp));
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
                while ((line = tempbr.readLine()) != null) {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
                bw.close();
                
                tempbr.close();
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.SAVEFILE, file.getAbsolutePath());
                this.temp.delete();
                this.temp = null;
                this.file = null;
            }
        }
    }
    
    class TextEditor extends Editor {
        
        public TextEditor(EventManager event_manager) {
            super(event_manager);
        }
        
        public void insertLine(int lineNumber, String text) {
            String[] file_text = new String[0];
            String line;
            int count = 0;
            boolean found = false;
            try {
                BufferedReader tempbr = new BufferedReader(new FileReader(this.temp));
                while ((line = tempbr.readLine()) != null) {
                    if (count == lineNumber) {
                        file_text = AppendToStringArr(file_text, text);
                        found = true;
                    }
                    file_text = AppendToStringArr(file_text, line);
                    count++;
                }
                if (!found)
                    file_text = AppendToStringArr(file_text, text);
                tempbr.close();
                
                BufferedWriter tempbw = new BufferedWriter(new FileWriter(this.temp));
                for (String l : file_text) {
                    tempbw.write(l);
                    tempbw.newLine();
                }
                tempbw.close();
            } catch (IOException ex) {
                Logger.getLogger(ObserverPattern.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                event_manager.notfy(Events.EDITFILE, file.getAbsolutePath());
            }
        }
        
        public void removeLine(int lineNumber) {
            String[] file_text = new String[0];
            String line;
            int count = 0;
            try {
                BufferedReader tempbr = new BufferedReader(new FileReader(this.temp));
                while ((line = tempbr.readLine()) != null) {
                    if (count == lineNumber) 
                        continue;
                    file_text = AppendToStringArr(file_text, line);
                    count++;
                }
                tempbr.close();
                
                BufferedWriter tempbw = new BufferedWriter(new FileWriter(this.temp));
                for (String l : file_text) {
                    tempbw.write(l);
                    tempbw.newLine();
                }
                tempbw.close();
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
    
    public String[] AppendToStringArr(String[] arr,String new_string) {
        arr = Arrays.copyOf(arr, arr.length+1);
        arr[arr.length - 1] = new_string;
        return arr;
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