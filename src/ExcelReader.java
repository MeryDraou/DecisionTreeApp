import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<MachineInfo> readExcel(String filePath) throws IOException {
        List<MachineInfo> machineInfoList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            MachineInfo machineInfo = new MachineInfo();
            machineInfo.setIp(row.getCell(0).getStringCellValue());
            machineInfo.setVlanId((int) row.getCell(1).getNumericCellValue());
            machineInfoList.add(machineInfo);
        }

        workbook.close();
        inputStream.close();
        return machineInfoList;
    }

}
