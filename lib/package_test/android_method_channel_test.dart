import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(PipExample());
}

class PipExample extends StatelessWidget {
  static const platform = MethodChannel('com.example.pip/pip');

  Future<void> enterPipMode() async {
    try {
      await platform.invokeMethod('enterPipMode');
    } on PlatformException catch (e) {
      print("Failed to enter PIP mode: ${e.message}");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('PIP Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: enterPipMode,
            child: Text('Enter PIP Mode'),
          ),
        ),
      ),
    );
  }
}
