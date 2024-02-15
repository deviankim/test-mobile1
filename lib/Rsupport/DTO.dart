
import 'package:flutter/widgets.dart';
import 'package:rxdart/rxdart.dart';



class DTO{
  bool isUpdated = false;

  String htmlTag;
  DTO(this.htmlTag);


  String? srcUrl;
  void trans() async
  {
    int idx_s = htmlTag.indexOf('src="') + 5;
    int idx_f = htmlTag.indexOf('"', idx_s);

    if(idx_s < 0 || idx_f < 0 || idx_f <= idx_s) return;

    srcUrl = htmlTag.substring(idx_s, idx_f);
    srcUrl = srcUrl!.replaceAll("&amp;", "&");
    update();
  }


  Widget? ImageView;
  Widget render()
  {
    if(ImageView == null) {
      print("render ${srcUrl}");
    }
    ImageView ??= Image.network(srcUrl!);
    return ImageView!;
  }



  late BehaviorSubject _subject = BehaviorSubject.seeded(this); //초기값: 0

  void update()
  {
    isUpdated = true;
    _subject.add(this);
  }

  
  ValueStream get stream => _subject.stream;
}