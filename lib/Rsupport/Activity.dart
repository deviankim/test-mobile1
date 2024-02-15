


import 'package:flutter/material.dart';
import 'package:rxdart/rxdart.dart';

import 'DTO.dart';
import 'Util.dart';

class Activity extends StatelessWidget {
  Observable observ_parsing = new Observable();
  Activity({super.key}){

    Util().parser_html().then((value){
      observ_parsing.update(val:value);
    });
  }

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      home: Scaffold(
        body: StreamBuilder(stream: observ_parsing.stream, 
          builder: (context, snapshot) {

            if(snapshot.data is List<DTO>) {
              return GridView.builder(
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 3, 
                  childAspectRatio: 1, 
                  mainAxisSpacing: 10, 
                  crossAxisSpacing: 10, 
                ),
                itemCount: snapshot.data.length,
                itemBuilder: (context, index) {
                  return VIEW_LISTITEM(snapshot.data[index], index);
                },
              );
            } else {
              return const Center(
                child: Text("Loading..."),
              );
            }
            
          },),
      ),
    );
    
  }

  Widget VIEW_LISTITEM(DTO data, int index)
  {

    return StreamBuilder(stream: data.stream, 
      builder: (context, snapshot) {
    
        if(snapshot.data == null || data.srcUrl == null) {
          return const Text("Loading Item...");
        } else {
          return data.render();
        }
        
      },);
  }

}



class Observable{
  List<DTO>? value = null;
  Observable({this.value});

  late BehaviorSubject _subject = BehaviorSubject.seeded(value); 

  void update({List<DTO>? val})
  {
    if(val != null)value = val;
    _subject.add(value);
  }
  
  ValueStream get stream => _subject.stream;
}