/**
 * Created by n.melnikov on 25.09.2017.
 */
import java.io.IOException;
import java.util.Scanner;

class InputData {
    private WorkWithFile workWithFile = new WorkWithFile();
    /**
     * Основной метод проверки введенных данных
     * @throws IOException
     */
    void inputData() throws IOException {

        Scanner in = new Scanner(System.in);
        String currency = "";
        while(true)
        {
            System.out.print("Enter currencies: ");
            currency = in.nextLine();
            if (currency.equals("q")) break;
            if (currency.equals("balance"))
            {
                workWithFile.currentBalance();
                System.out.println(WorkWithFile.readFile(Const.pathToCurrentBalance));
                continue;
            }
            checkData(currency);
        }
    }

    /**
     * Проверка введенных данных на ошибки
     * @param data введенные данные
     * @throws IOException
     */
    private void checkData(String data) throws IOException {
        String []arr = workWithFile.tokenizer(data, " ");
        if (arr.length != 2)
        {
            System.out.println("На вход допускается только строки вида: Currency SUM");
            return;

        }
        if (!WorkWithFile.readFile(Const.pathToCurrency).contains(arr[0].toUpperCase()))
        {
            System.out.println("На вход допускается только строки вида: Currency SUM");
            System.out.println("Поддерживаемые валюты: \n"+ WorkWithFile.readFile(Const.pathToCurrency));
            return;
        }
        try
        {
            Integer.parseInt(arr[1]);
        }
        catch (Exception e)
        {
            System.out.println("На вход допускается только строки вида: Currency SUM");
            return;
        }
        WorkWithFile.writeFile(data.toUpperCase(), Const.pathToHistory);
    }
}
