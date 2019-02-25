package com.it.academy.task6;

import com.it.academy.task6.dto.BookDto;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class CsvUtils {

    public static void writeFile(final List<BookDto> books, HttpServletResponse response) {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), UTF_8)) {
            CSVFormat csvFileFormat = CSVFormat.DEFAULT
                    .withDelimiter(',')
                    .withRecordSeparator("\n");

            CSVPrinter printer = new CSVPrinter(outputStreamWriter, csvFileFormat);
            for (BookDto bookDto : books) {
                printer.printRecord(bookDto);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
