import 'package:flutter/material.dart';
import '../services/auth_service.dart';

class RegisterScreen extends StatelessWidget {
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
                await _authService.register(emailController.text, passwordController.text);
                Navigator.pop(context);
              },
              child: Text("Cadastrar"),
            ),
          ],
        ),
      ),
    );
  }
}
