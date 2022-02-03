import java.util.*;

public class SymTable {
    public List<HashMap<String,Sym>> symTable;

    /**
     * SymTable Constructor
     */
    public SymTable(){
        symTable = new ArrayList<HashMap<String,Sym>>();
        symTable.add(new HashMap<String,Sym>());
    }

    /**
     * if symTable is empty, throw EmptySymTableException
     * if name or sym are null throw IllegalArgumentException
     * if 1st hashMap contains paramter as a key throw DuplicateSyMException
     * Adds given name and Sym to ethe first HashMap in List
     * @param name (key)
     * @param sym (value)
     * @throws DuplicateSymException
     * @throws EmptySymTableException
     */
    public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException {
        if(symTable.isEmpty()) {throw new EmptySymTableException();}
        if(name == null || sym == null) { throw new IllegalArgumentException();}
        if(symTable.get(0).containsKey(name)){ throw new DuplicateSymException();}
        symTable.get(0).put(name, sym);
    }

    /**
     * Adds an empty HashMap to beginning of Symbol Table
     */
    public void addScope(){
        symTable.add(0, new HashMap<String,Sym>());
    }

    /**
     * If symTable is empty, throw Empty SymTableException
     * if 1st HashMap in List Contains key, return associated Sym else null
     * @param name (key)
     * @return (Associated Value of key)
     * @throws EmptySymTableException
     */
    public Sym lookupLocal(String name) throws EmptySymTableException{
        if(symTable.isEmpty()){ throw new EmptySymTableException();}
        if(symTable.get(0).containsKey(name)){ return symTable.get(0).get(name);}
        return null;

    }

    /**
     * If symTable is empty, throw Empty SymTableException
     * If any HashMap in the list contains name as a key, return the first associated Sym else return null
     * @param name (key)
     * @return (Associated Value of key)
     * @throws EmptySymTableException
     */
    public Sym lookupGlobal(String name)  throws EmptySymTableException{
        if(symTable.isEmpty()){ throw new EmptySymTableException();}
        for(int i = 0; i < symTable.size(); i++){
            if(symTable.get(i).containsKey(name)){
                return symTable.get(i).get(name);
            }
        }
        return null;
    }

    /**
     * If this SymTable's list is empty, throw an EmptySymTableException; 
     * otherwise, removes the HashMap from the front of the list. 
     * To clarify, throw an exception only if before attempting to remove, the list is empty (i.e. there are no HashMaps to remove).
     * 
     * @throws EmptySymTableException
     */
    public void removeScope() throws EmptySymTableException {
        if(symTable.isEmpty()){throw new EmptySymTableException();}
        symTable.remove(0);
    }

    /**
     * Prints SymTables
     */
    public void print(){
        System.out.println("\nSym Table\n");
        for(int i = 0; i < symTable.size();i++){
            System.out.println(symTable.get(i).toString());
        }
        System.out.println();
    }
}
