package br.com.marcell.coreutil.colecoes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import br.com.marcell.coreutil.enums.SortTypeEnum;
import br.com.marcell.coreutil.exception.BusinessException;
import br.com.marcell.coreutil.generic.GenericComparator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public final class Colecoes {
	public static List removerItem(Collection colecao, String nomeCampo, Object valor) {
			try {
				List retorno = (List) colecao.getClass().newInstance();
			
				for (Iterator it = colecao.iterator(); it.hasNext();) {
					Object o = it.next();
					Method m = o.getClass().getMethod("get" + WordUtils.capitalize(nomeCampo), null);
					Object r = m.invoke(o, null);
					if ((r != null) && (r.equals(valor)))
						continue;
					retorno.add(o);
				}
			return retorno;
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				throw new BusinessException(e.getMessage());
			}
		
	}

	public static List ordenar(List colecao, String nomeCampo, SortTypeEnum sortType) {
		if (colecao.size() <= 0)
			return colecao;
		Comparator comparator = new GenericComparator<>(nomeCampo, sortType);
		Collections.sort(colecao, comparator);
		return colecao;
	}

	public static boolean isEmpty(Collection colecao) {
		if (colecao == null)
			return true;

		boolean result = true;
		for (Iterator i$ = colecao.iterator(); i$.hasNext();) {
			Object obj = i$.next();
			if (obj == null)
				result = true;
			else
				return false;
		}
		return result;
	}
}