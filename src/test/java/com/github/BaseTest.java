package com.github;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.opencsv.CSVReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {
    ClassLoader classLoader = BaseTest.class.getClassLoader();

    @Test
    void zipParsingPDFTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/PDF.zip"));
        try (ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("PDF.zip")))) {
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("sample.pdf");
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    PDF pdf = new PDF(inputStream);
                    Assertions.assertThat(pdf.numberOfPages).isEqualTo(2);
                    assertThat(pdf, new ContainsExactText("A Simple PDF File"));
                }
            }
        }
    }

    @Test
    void zipParsingPngTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/PNG.zip"));
        try (ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("PNG.zip")))) {
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("Sample.png");
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    BufferedImage img = ImageIO.read(inputStream);
                    Assertions.assertThat(img.getHeight()).isEqualTo(1467);
                    Assertions.assertThat(img.getWidth()).isEqualTo(2200);
                }
            }
        }
    }
    @Test
    void zipParsingCsvTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/CSV.zip"));
        try (ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("CSV.zip")))) {
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("addresses.csv");
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = reader.readAll();
                        Assertions.assertThat(content).contains(
                                new String[]{"John","Doe","120 jefferson st.","Riverside", " NJ", " 08075"}
                        );
                    }
                }
            }
        }
    }
}