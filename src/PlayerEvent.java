public interface PlayerEvent {
    void onButtonClick(Player player, Field field);
    void onPlayerMoves(Field before, Field after);
}
