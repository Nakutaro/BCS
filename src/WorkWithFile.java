/**
 * Created by n.melnikov on 25.09.2017.
 */
import java.io.*;

import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

class WorkWithFile {
    /**
     * Запись в файл
     * @param text строка записи
     * @param path путь к файлу
     */
    static void writeFile(String text, String path) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(path, true))
        {
            if (!file.exists())
            {
                file.createNewFile();
            }
            writer.write(text+"\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Чтение файла
     * @param path путь до файла
     * @return
     * @throws NullPointerException
     */
    static String readFile(String path) throws NullPointerException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            //Объект для чтения файла в буфер
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s).append("\n");
                }
            }
            //Также не забываем закрыть файл

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    /**
     * Текущий баланс на счете
     */
    void currentBalance()
    {
        clearDocument(Const.pathToCurrentBalance);
        String currency[] = tokenizer(readFile(Const.pathToCurrency), "\n");
        String historyOperation[] = tokenizer(readFile(Const.pathToHistory), "\n");
        for (int j = 0; j < currency.length; j++)
        {
            int sum = 0;
            for (String aHistoryOperation : historyOperation) {
                String operation[] = tokenizer(aHistoryOperation, " ");

                if (currency[j].equals(operation[0])) {
                    sum += parseInt(operation[1]);
                }
            }
            if (sum!=0)
                writeFile(currency[j] = currency[j]+" "+sum, Const.pathToCurrentBalance);
        }

    }

    /**
     * Разделение строки на части
     * @param data строка или множество строк
     * @param delim разделитель
     * @return массив разделенных частей
     */
    String[] tokenizer(String data, String delim)
    {
        StringTokenizer stk = new StringTokenizer(data,delim);
        String []arr = new String[stk.countTokens()];
        for(int i = 0; i<arr.length; i++)
        {
            arr[i] = stk.nextToken();
        }
        return arr;
    }

    /**
     * Очистка документа
     * @param path путь до документа
     */
    private void clearDocument(String path)
    {
        try {
            FileWriter fstream1 = new FileWriter(path);
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());}
    }

}
