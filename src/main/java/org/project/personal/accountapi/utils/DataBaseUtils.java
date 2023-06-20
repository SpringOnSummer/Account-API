package org.project.personal.accountapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseUtils {
    public static List<String> convertSqlScriptsToList(String classpath) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(classpath);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));

        char[] bufferSize = new char[64];

        StringBuilder builder = new StringBuilder();

        int length;
        while ((length = bufferedReader.read(bufferSize)) > -1){
            builder.append(bufferSize,0,length);

        }

        StringTokenizer stringTokenizer = new StringTokenizer(builder.toString(),";");

        List<String> sql = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()){
            sql.add(stringTokenizer.nextToken());
        }

        return sql;
    }
}
