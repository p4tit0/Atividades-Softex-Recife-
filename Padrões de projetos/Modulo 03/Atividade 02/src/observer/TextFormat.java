package observer;


/**
 * Funções para facilitar a formatação de títulos e mensagens no console
 * @author Vinícius Santos Lima
 */
public class TextFormat 
{
    public static final String ANSI_RESET = "\u001B[0m";
    
    private static int get_color_modifier(String color)
    {
        switch (color)
        {
            case "BLACK":
                return 0;
            case "RED":
                return 1;
            case "GREEN":
                return 2;
            case "YELLOW":
                return 3;
            case "BLUE":
                return 4;
            case "MAGENTA":
                return 5;
            case "CYAN":
                return 6;
            case "WHITE":
                return 7;
            default:
                return 9;
        }
    }
    

    public static String get_format(String... params)
    {
        String bg_color = params.length > 0 ? params[0] : "NONE";
        String txt_color = params.length > 1 ? params[1] : "NONE";
        String txt_format = params.length > 2 ? params[2] : "NONE";
        
        String split_str[] = txt_color.strip().toUpperCase().split(" ");
        
        int txt_color_num = 30;
        if (split_str[0] == "BRIGHT")
            txt_color_num = 90;
        
        txt_color_num += get_color_modifier(split_str[split_str.length - 1]);
        
        split_str = bg_color.strip().toUpperCase().split(" ");
        int bg_color_num = 40;
        if (split_str[0] == "BRIGHT")
            bg_color_num = 100;
        
        bg_color_num += get_color_modifier(split_str[split_str.length - 1]);
        
        String format_str = String.format("\033[%d;%d", bg_color_num, txt_color_num);
        
        split_str = txt_format.toUpperCase().split("\\+");
        for (String format: split_str)
            switch (format)
            {
                case "RESET":
                    format_str += ";0";
                    break;
                case "BOLD":
                    format_str += ";1";
                    break;
                case "FAINT":
                    format_str += ";2";
                    break;
                case "ITALIC":
                    format_str += ";3";
                    break;
                case "UNDERLINE":
                    format_str += ";4";
                    break;
                case "SLOW BLINK":
                    format_str += ";5";
                    break;
                case "RAPID BLINK":
                    format_str += ";6";
                    break;
                case "INVERT":
                    format_str += ";7";
                    break;
                case "HIDE":
                    format_str += ";8";
                    break;
                case "STRIKE":
                    format_str += ";9";
                    break;
                case "DOUBLE UNDERLINE":
                    format_str += ";21";
                    break;
                default:
                    format_str += ";10";
                    break;
            }
        
        format_str += "m";
        return format_str;
    }
    

    public static String title(int size, String txt, String... kargs)
    {
        String line = kargs.length > 0 ? kargs[0] : "-=-";
        String txt_bg_color = kargs.length > 1 ? kargs[1] : "NONE";
        String txt_color = kargs.length > 2 ? kargs[2] : "NONE";
        String txt_format = kargs.length > 3 ? kargs[3] : "NONE";
        String line_bg_color = kargs.length > 4 ? kargs[4] : txt_bg_color;
        String line_color = kargs.length > 5 ? kargs[5] : txt_color;
        String line_format = kargs.length > 6 ? kargs[6] : txt_format;

        String txt_format_str = get_format(txt_bg_color, txt_color, txt_format);
        String line_format_str = get_format(line_bg_color, line_color, line_format);

        String raw_div = new String(new char[(int)(size/line.length())]).replace("\0", line);
        String div = line_format_str + raw_div + line.substring(0, size%line.length()) + ANSI_RESET ; 
        
        String result = div + "\n";
        for (String title_line: txt.split("\n"))
        {
            int n_spaces = ((int)(size/2)) - ((int)(title_line.length()/2));
            String spaces = new String(new char[n_spaces + ((title_line.length() % 2) * (-1) + 1)]).replace("\0", " ");
            
            result += txt_format_str + spaces.substring(((title_line.length() % 2) * (-1) + 1)) + txt + spaces +ANSI_RESET + "\n";
        }
        result += div;
        return result;
    }
    
    
    public static String msg1(int size, String title, String txt, String... kargs)
    {
        String line = kargs.length > 0 ? kargs[0] : "-";
        String txt_bg_color = kargs.length > 1 ? kargs[1] : "NONE";
        String txt_color = kargs.length > 2 ? kargs[2] : "NONE";
        String txt_format = kargs.length > 3 ? kargs[3] : "NONE";
        String line_bg_color = kargs.length > 4 ? kargs[4] : txt_bg_color;
        String line_color = kargs.length > 5 ? kargs[5] : txt_color;
        String line_format = kargs.length > 6 ? kargs[6] : txt_format;

        String txt_format_str = get_format(txt_bg_color, txt_color, txt_format);
        String line_format_str = get_format(line_bg_color, line_color, line_format);
        
        int num_line = ((int)((size/line.length())/2)) - ((int)(title.length()/2)) - 1;
        String half_line = new String(new char[num_line]).replace("\0", line);
        
        String result = line_format_str + half_line.substring(0, num_line - (title.length() % 2) ) + " " + title + " " + half_line + "\n";
        for (String msg_line: txt.split("\n"))
        {
            int n_spaces = ((int)(size/2)) - ((int)(msg_line.length()/2));
            String spaces = new String(new char[n_spaces]).replace("\0", " ");
            
            result += txt_format_str + spaces.substring(0, n_spaces - (msg_line.length() % 2)) + txt + spaces +ANSI_RESET + "\n";
        }
        String raw_div = new String(new char[(int)(size/line.length())]).replace("\0", line);
        result += line_format_str + raw_div + line.substring(0, size%line.length()) + ANSI_RESET;
        return result;
    }

}