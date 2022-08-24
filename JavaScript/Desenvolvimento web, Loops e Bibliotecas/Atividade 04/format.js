function get_format(bg_color = 'NONE', txt_color = 'NONE', txt_format = 'NONE')
{
    const txt_color_spit = txt_color.toUpperCase().split(' ');

    var txt_color_num = 30;
    if (txt_color_spit[0] === 'BRIGHT')
        txt_color_num = 90;

    switch (txt_color_spit[txt_color_spit.length - 1])
    {
        case 'BLACK':
            txt_color_num += 0;
            break;
        case 'RED':
            txt_color_num += 1;
            break;
        case 'GREEN':
            txt_color_num += 2;
            break;
        case 'YELLOW':
            txt_color_num += 3;
            break;
        case 'BLUE':
            txt_color_num += 4;
            break;
        case 'MAGENTA':
            txt_color_num += 5;
            break;
        case 'CYAN':
            txt_color_num += 6;
            break;
        case 'WHITE':
            txt_color_num += 7;
            break;
        default:
            txt_color_num += 9;
            break;
    }

    const bg_color_split = bg_color.toUpperCase().split(' ');

    var bg_color_num = 40;
    if (bg_color_split[0] === 'BRIGHT')
        bg_color_num = 100;

    switch (bg_color_split[bg_color_split.length - 1])
    {
        case 'BLACK':
            bg_color_num += 0;
            break;
        case 'RED':
            bg_color_num += 1;
            break;
        case 'GREEN':
            bg_color_num += 2;
            break;
        case 'YELLOW':
            bg_color_num += 3;
            break;
        case 'BLUE':
            bg_color_num += 4;
            break;
        case 'MAGENTA':
            bg_color_num += 5;
            break;
        case 'CYAN':
            bg_color_num += 6;
            break;
        case 'WHITE':
            bg_color_num += 7;
            break;
        default:
            bg_color_num += 9;
            break;
    }

    const formats = txt_format.split('+');
    var txt_formats_numbers = [];
    for (const format of formats)
    {
        switch (format.toUpperCase())
        {
            case 'RESET':
                txt_formats_numbers.push(0);
                break;
            case 'BOLD':
                txt_formats_numbers.push(1);
                break;
            case 'FAINT':
                txt_formats_numbers.push(2);
                break;
            case 'ITALIC':
                txt_formats_numbers.push(3);
                break;
            case 'UNDERLINE':
                txt_formats_numbers.push(4);
                break;
            case 'SLOW BLINK':
                txt_formats_numbers.push(5);
                break;
            case 'RAPID BLINK':
                txt_formats_numbers.push(6);
                break;
            case 'INVERT':
                txt_formats_numbers.push(7);
                break;
            case 'HIDE':
                txt_formats_numbers.push(8);
                break;
            case 'STRIKE':
                txt_formats_numbers.push(9);
                break;
            case 'DOUBLE UNDERLINE':
                txt_formats_numbers.push(21);
                break;
            default:
                txt_formats_numbers.push(10);
                break;
        }
    }

    var format_str = '\033[' + bg_color_num + ';' + txt_color_num;

    for (const format of txt_formats_numbers)
        format_str += ';' + format;
    format_str += 'm';
    return format_str;
}

function center(txt, size, char)
{
    return txt.padEnd(Number.parseInt(size/2) + Number.parseInt(txt.length/2), char).padStart(size, char)
}

function add_format_to_center_txt(format, txt, size, char = ' ', callback = center)
{   
    const line = callback(txt, size, char);
    return format + line + "\033[0m";
}
function add_format(format, txt)
{   
    return format + txt + "\033[0m";
}

function title(size, txt, line, {
    txt_bg_color = 'NONE',
    txt_color = 'NONE',
    txt_format = 'NONE',
    line_bg_color = null,
    line_color = null,
    line_format = null
  }={})
{

    if (line_bg_color == null)
        line_bg_color = txt_bg_color;
    if (line_color == null)
        line_color = txt_color;
    if (line_format == null)
        line_format = txt_format;

    const txt_format_str = get_format(txt_bg_color, txt_color, txt_format);
    const line_format_str = get_format(line_bg_color, line_color, line_format);

    
    var result = add_format(line_format_str, line.repeat(Number.parseInt(size / line.length)) + line.slice(0, size % line.length)) + "\n";
    for (const title_line of txt.split('\n'))
        result += add_format_to_center_txt(txt_format_str, title_line, size) + "\n";
    result += add_format(line_format_str, line.repeat(Number.parseInt(size / line.length)) + line.slice(0, size % line.length));
    return result;
}

function msg(size, title, txt, line, {
    txt_bg_color = 'NONE',
    txt_color = 'NONE',
    txt_format = 'NONE',
    line_bg_color = null,
    line_color = null,
    line_format = null
  }={})
{

    if (line_bg_color == null)
        line_bg_color = txt_bg_color;
    if (line_color == null)
        line_color = txt_color;
    if (line_format == null)
        line_format = txt_format;
    
    const txt_format_str = get_format(txt_bg_color, txt_color, txt_format);
    const line_format_str = get_format(line_bg_color, line_color, line_format);

    var result = add_format_to_center_txt(line_format_str, ' '+ title +' ', size, line) + "\n";
    for (const message_line of txt.split('\n'))
        result += add_format_to_center_txt(txt_format_str, message_line, size) + "\n";
    result += add_format(line_format_str, line.repeat(Number.parseInt(size / line.length)) + line.slice(0, size % line.length));
    return result;
}

module.exports = { get_format, center, title, msg };