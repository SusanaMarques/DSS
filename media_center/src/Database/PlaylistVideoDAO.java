package Database;

import Business.Musica;
import Business.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PlaylistVideoDAO implements Map<Integer, List<Video>>{
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public List<Video> get(Object o) {
        return null;
    }

    @Override
    public List<Video> put(Integer integer, List<Video> videos) {
        return null;
    }

    @Override
    public List<Video> remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends List<Video>> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<List<Video>> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, List<Video>>> entrySet() {
        return null;
    }
}
    /*
{
    Connection c;


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public Video get(Object o) {
        return null;
    }

    @Override
    public Video put(Integer integer, Video video) {
        return null;
    }

    @Override
    public Video remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Video> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<Video> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, Video>> entrySet() {
        return null;
    }
}
*/