import 'package:flutter/material.dart';
import '../services/auth_service.dart';
import 'home_screen.dart';

class LoginScreen extends StatelessWidget {
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final AuthService _authService = AuthService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            TextField(controller: emailController, decoration: InputDecoration(labelText: 'E-mail')),
            TextField(controller: passwordController, decoration: InputDecoration(labelText: 'Senha')),
            ElevatedButton(
              onPressed: () async {
                var user = await _authService.login(emailController.text, passwordController.text);
                if (user != null) {
                  Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => HomeScreen()));
                }
              },
              child: Text("Entrar"),
            ),
          ],
        ),
      ),
    );
  }
}
