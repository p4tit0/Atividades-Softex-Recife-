
package exceção;


/**
 *
 * @author space
 */
public class TextFormat 
{
    public static final String ANSI_RESET = "\u001B[0m";
    
    //String bg_color, String txt_color, String txt_format
    public static String get_format(String... params)
    {
        String bg_color = params.length > 0 ? params[0] : "NONE";
        String txt_color = params.length > 1 ? params[1] : "NONE";
        String txt_format = params.length > 2 ? params[2] : "NONE";
        
        String split_str[] = txt_color.toUpperCase().split(" ");
        int txt_color_num = 30;
        if (split_str[0] == "BRIGHT")
            txt_color_num = 90;

        switch (split_str[split_str.length - 1])
        {
            case "BLACK":
                txt_color_num += 0;
                break;
            case "RED":
                txt_color_num += 1;
                break;
            case "GREEN":
                txt_color_num += 2;
                break;
            case "YELLOW":
                txt_color_num += 3;
                break;
            case "BLUE":
                txt_color_num += 4;
                break;
            case "MAGENTA":
                txt_color_num += 5;
                break;
            case "CYAN":
                txt_color_num += 6;
                break;
            case "WHITE":
                txt_color_num += 7;
                break;
            default:
                txt_color_num += 9;
                break;
        }
        
        split_str = bg_color.toUpperCase().split(" ");
        int bg_color_num = 40;
        if (split_str[0] == "BRIGHT")
            bg_color_num = 100;
        switch (split_str[split_str.length - 1])
        {
            case "BLACK":
                bg_color_num += 0;
                break;
            case "RED":
                bg_color_num += 1;
                break;
            case "GREEN":
                bg_color_num += 2;
                break;
            case "YELLOW":
                bg_color_num += 3;
                break;
            case "BLUE":
                bg_color_num += 4;
                break;
            case "MAGENTA":
                bg_color_num += 5;
                break;
            case "CYAN":
                bg_color_num += 6;
                break;
            case "WHITE":
                bg_color_num += 7;
                break;
            default:
                bg_color_num += 9;
                break;
        }
        
        String format_str = String.format("\u001B[%d;%d", bg_color_num, txt_color_num);
        
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
    /*
    def msg2(size: int, txt: str, bg_color: str = '', txt_color: str = '', txt_format: str = ''):
        txt_format_str = get_format(bg_color, txt_color, txt_format)

        for message in txt.split('\n'):
            print(txt_format_str + message + " " * (size - len(message)) + "\033[0m")
    */
}
