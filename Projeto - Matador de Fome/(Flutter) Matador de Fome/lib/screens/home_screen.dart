import 'package:flutter/material.dart';
import '../services/firestore_service.dart';
import '../models/category.dart';
import '../models/product.dart';

class HomeScreen extends StatelessWidget {
  final FirestoreService _firestoreService = FirestoreService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Matador de Fome')),
      body: Column(
        children: [
          StreamBuilder<List<Category>>(
            stream: _firestoreService.getCategories(),
            builder: (context, snapshot) {
              if (!snapshot.hasData) return CircularProgressIndicator();
              List<Category> categories = snapshot.data!;
              return ListView.builder(
                shrinkWrap: true,
                itemCount: categories.length,
                itemBuilder: (context, index) {
                  return ListTile(title: Text(categories[index].name));
                },
              );
            },
          ),
        ],
      ),
    );
  }
}
