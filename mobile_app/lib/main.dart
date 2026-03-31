import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class Etudiant {
  final int id;
  final String cin;
  final String nom;
  final String dateNaissance;

  Etudiant({
    required this.id,
    required this.cin,
    required this.nom,
    required this.dateNaissance,
  });

  factory Etudiant.fromJson(Map<String, dynamic> json) {
    return Etudiant(
      id: json['id'],
      cin: json['cin'],
      nom: json['nom'],
      dateNaissance: json['dateNaissance'].toString(),
    );
  }
}

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        scaffoldBackgroundColor: const Color(0xFFF8F8F8),
      ),
      home: const EtudiantPage(),
    );
  }
}

class EtudiantPage extends StatefulWidget {
  const EtudiantPage({super.key});

  @override
  State<EtudiantPage> createState() => _EtudiantPageState();
}

class _EtudiantPageState extends State<EtudiantPage> {
  List<Etudiant> etudiants = [];
  bool loading = true;
  bool hasError = false;

  @override
  void initState() {
    super.initState();
    fetchEtudiants();
  }

  Future<void> fetchEtudiants() async {
    setState(() { loading = true; hasError = false; });
    try {
      final response = await http.get(
        Uri.parse('http://localhost:8081/api/etudiants'),
      );
      if (response.statusCode == 200) {
        final List data = jsonDecode(response.body);
        setState(() {
          etudiants = data.map((e) => Etudiant.fromJson(e)).toList();
          loading = false;
        });
      } else {
        setState(() { hasError = true; loading = false; });
      }
    } catch (e) {
      setState(() { hasError = true; loading = false; });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0.5,
        centerTitle: true,
        title: const Text(
          'Liste des Étudiants',
          style: TextStyle(
            color: Color(0xFF1A1A1A),
            fontWeight: FontWeight.bold,
            fontSize: 18,
          ),
        ),
        actions: [
          TextButton(
            onPressed: fetchEtudiants,
            child: const Text(
              'Actualiser',
              style: TextStyle(color: Color(0xFFFF6B6B)),
            ),
          ),
        ],
      ),
      body: loading
          ? const Center(
              child: CircularProgressIndicator(color: Color(0xFFFF6B6B)),
            )
          : hasError
              ? Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text(
                        'Erreur de connexion',
                        style: TextStyle(color: Colors.grey, fontSize: 16),
                      ),
                      const SizedBox(height: 16),
                      TextButton(
                        onPressed: fetchEtudiants,
                        child: const Text(
                          'Réessayer',
                          style: TextStyle(color: Color(0xFFFF6B6B)),
                        ),
                      ),
                    ],
                  ),
                )
              : ListView.separated(
                  padding: const EdgeInsets.symmetric(
                      vertical: 12, horizontal: 16),
                  itemCount: etudiants.length,
                  separatorBuilder: (_, __) => const SizedBox(height: 10),
                  itemBuilder: (context, index) {
                    final e = etudiants[index];
                    return Container(
                      padding: const EdgeInsets.all(16),
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(16),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black.withOpacity(0.04),
                            blurRadius: 8,
                            offset: const Offset(0, 2),
                          ),
                        ],
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            e.nom,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 15,
                              color: Color(0xFF1A1A1A),
                            ),
                          ),
                          const SizedBox(height: 4),
                          Text(
                            'CIN : ${e.cin}',
                            style: const TextStyle(
                              fontSize: 13,
                              color: Colors.grey,
                            ),
                          ),
                          const SizedBox(height: 2),
                          Text(
                            'Né le : ${e.dateNaissance}',
                            style: const TextStyle(
                              fontSize: 13,
                              color: Colors.grey,
                            ),
                          ),
                        ],
                      ),
                    );
                  },
                ),
    );
  }
}