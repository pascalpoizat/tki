#!/bin/bash

# Configuration
# Adaptez ici si le nom de votre repo change
REPO="pascalpoizat/tki"

# Dossier d'installation local utilisateur
INSTALL_DIR="$HOME/.tki"
BIN_DIR="$INSTALL_DIR/bin"

# URLs de téléchargement (pointent toujours vers la "latest release" publiée sur GitHub)
# NOTE : Le script s'attend à trouver "app-1.0.0.jar" et le script "tki" dans les assets de la release.
JAR_URL="https://github.com/$REPO/releases/latest/download/app-1.0.0.jar"
SCRIPT_URL="https://github.com/$REPO/releases/latest/download/tki"

echo "🚀 Installation de TKI..."

# 1. Création des dossiers
mkdir -p "$BIN_DIR"

# 2. Téléchargement
echo "⬇️  Téléchargement de l'application..."

# Fonction utilitaire pour télécharger (compatible curl ou wget)
download_file() {
    local url="$1"
    local dest="$2"
    if command -v curl >/dev/null 2>&1; then
        # -sL : silencieux + suivre les redirections (crucial pour GitHub Releases)
        curl -sL -o "$dest" "$url"
    elif command -v wget >/dev/null 2>&1; then
        wget -q -O "$dest" "$url"
    else
        echo "❌ Erreur : Ni curl ni wget ne sont installés sur votre système."
        exit 1
    fi
}

download_file "$JAR_URL" "$BIN_DIR/app-1.0.0.jar"
download_file "$SCRIPT_URL" "$BIN_DIR/tki"

# Vérification simple que le téléchargement a fonctionné (fichier non vide)
if [ ! -s "$BIN_DIR/app-1.0.0.jar" ]; then
    echo "❌ Erreur : Le téléchargement du fichier Jar a échoué (fichier vide ou absent)."
    echo "   Vérifiez que la Release est bien publiée sur GitHub et contient 'app-1.0.0.jar'."
    exit 1
fi

# 3. Permissions d'exécution
chmod +x "$BIN_DIR/tki"

# 4. Ajout au PATH
SHELL_CONFIG=""
case "$SHELL" in
  */zsh) SHELL_CONFIG="$HOME/.zshrc" ;;
  */bash) SHELL_CONFIG="$HOME/.bashrc" ;;
  *) SHELL_CONFIG="$HOME/.profile" ;;
esac

if [ -n "$SHELL_CONFIG" ]; then
    # On évite d'ajouter le path s'il y est déjà
    if ! grep -q "$BIN_DIR" "$SHELL_CONFIG"; then
        echo "" >> "$SHELL_CONFIG"
        echo "# TKI path added by install script" >> "$SHELL_CONFIG"
        echo "export PATH=\"\$PATH:$BIN_DIR\"" >> "$SHELL_CONFIG"
        echo "✅ Chemin ajouté à $SHELL_CONFIG"
        echo "   (Redémarrez votre terminal ou tapez 'source $SHELL_CONFIG' pour activer)"
    else
        echo "✅ Le chemin est déjà configuré dans $SHELL_CONFIG."
    fi
else
    echo "⚠️  Impossible de détecter le fichier de configuration du shell."
    echo "   Vous devrez ajouter manuellement : export PATH=\"\$PATH:$BIN_DIR\""
fi

echo ""
echo "🎉 Installation terminée !"
echo "👉 Vous pouvez maintenant utiliser la commande :"
echo "   tki \"Pascal Poizat\""