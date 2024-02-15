
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:http/io_client.dart';
import 'DTO.dart';





class Util {

  Future<List<DTO>> parser_html() async {
    http.Response result = await GET("https://www.gettyimages.com/photos/collaboration", input_header: {
      "Cache-Control": "no-cache",
      "Content-Encoding": "gzip",
      "Content-Type": "text/html; charset=utf-8",
      "Date": "Tue, 13 Feb 2024 15:35:12 GMT",
      "Referrer-Policy": "strict-origin-when-cross-origin",
      "Selected-Fe": "getty_frontend",
      "Server": "nginx/1.24.0",
    });


    List<DTO> returns = List.empty(growable: true);

    String body = result.body;
    String pattern = "</picture>";
    int pattern_len = pattern.length;

    // print("-- ${body} -- ");

    int idx_last = 0;
    for(int i_ins = 0; i_ins < 100; i_ins++) {
      if(idx_last < 0) break;
      

      int idx_s = body.indexOf("""<picture>""", idx_last);
      int idx_f = body.indexOf("""</picture>""", idx_s+pattern_len);

      if(idx_s < 0 || idx_f < 0 || idx_f <= idx_s) break;

      // print("-- S: ${idx_s} -- ");
      // print("-- F: ${idx_f} -- ");

      idx_last = idx_f+pattern_len;
      String htmlTag = body.substring(idx_s, idx_last);
      returns.add(new DTO(htmlTag)..trans());

      
      // print("-- ${htmlTag} -- ");
      
    }

    return returns;

    

  }


  Future<http.Response> GET(String input_address, {Map<String, String>? input_header }) async {


      
    var urls = Uri.parse(input_address);

    final ioc = new HttpClient();
    ioc.badCertificateCallback = (X509Certificate cert, String host, int port) => true;
    ioc.connectionTimeout = new Duration(seconds: 5);
    final https = new IOClient(ioc);

    http.Response returns = await https.get(
      urls,
      headers: input_header,
    ).timeout(new Duration(seconds: 5));
    //KsLogger.M().debug("MsLog", returns.body);

    return returns;
  }



}


