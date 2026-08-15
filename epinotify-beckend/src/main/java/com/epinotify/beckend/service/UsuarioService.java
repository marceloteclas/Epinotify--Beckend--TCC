package com.epinotify.beckend.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.epinotify.beckend.model.Usuario;
import com.epinotify.beckend.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado com o ID: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado."));
    }

    @Transactional(readOnly = true)
    public boolean emailJaCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Transactional
    public Usuario criar(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException(
                    "Já existe um usuário cadastrado com este e-mail.");
        }

        if (usuario.getSenha() == null
                || usuario.getSenha().isBlank()) {

            throw new IllegalArgumentException(
                    "A senha é obrigatória.");
        }

        usuario.setId(null);
        usuario.setAtivo(true);

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {

        Usuario usuario = buscarPorId(id);

        if (!usuario.getEmail().equalsIgnoreCase(dadosAtualizados.getEmail())
                && usuarioRepository.existsByEmail(dadosAtualizados.getEmail())) {

            throw new IllegalArgumentException(
                    "Já existe um usuário cadastrado com este e-mail.");
        }

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());

        if (dadosAtualizados.getAtivo() != null) {
            usuario.setAtivo(dadosAtualizados.getAtivo());
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void desativar(Long id) {

        Usuario usuario = buscarPorId(id);

        usuario.setAtivo(false);

        usuarioRepository.save(usuario);
    }
}