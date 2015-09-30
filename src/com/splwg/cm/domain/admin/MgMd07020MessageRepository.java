package com.splwg.cm.domain.admin;

import com.splwg.base.domain.common.message.MessageParameters;
import com.splwg.shared.common.ServerMessage;

import java.math.BigInteger;

/**
 * @messageRepository
 */
public class MgMd07020MessageRepository extends MgMd07020Messages{
    private static MgMd07020MessageRepository instance;
    private static MgMd07020MessageRepository getInstance(){
        if (instance == null) {
            instance = new MgMd07020MessageRepository();
        }
        return instance;
    }

    /**
     * @message SP_CHAR_TYPE_NOT_EXISTS
     * @description ENG "«Тип характеристики %1 не существует»" "«Тип характеристики %1 не существует»"
     * @description RUS "«Тип характеристики %1 не существует»" "«Тип характеристики %1 не существует»"
     * @description RUM "«Тип характеристики %1 не существует»" "«Тип характеристики %1 не существует»"
     */
    public static ServerMessage spCharTypeNotExist(String charType)
    {
    	MessageParameters params = new MessageParameters();
		params.addRawString(charType);
        return getInstance().getMessage(SP_CHAR_TYPE_NOT_EXISTS);
    }
    
    /**
	 * @message SP_FOR_CHAR_TYPE_NOT_EXISTS
	 * @description ENG "«Типу характеристики %1 не назначен объект «ТУ»" "«Типу характеристики %1 не назначен объект «ТУ»"
	 * @description RUS "«Типу характеристики %1 не назначен объект «ТУ»" "«Типу характеристики %1 не назначен объект «ТУ»"
	 * @description RUM "«Типу характеристики %1 не назначен объект «ТУ»" "«Типу характеристики %1 не назначен объект «ТУ»"
	 */
	public static ServerMessage saForCharTypeNotExists(String charType) 
	{
		MessageParameters params = new MessageParameters();
		params.addRawString(charType);
		return getInstance().getMessage(SP_FOR_CHAR_TYPE_NOT_EXISTS, params);		
	}
	
    /**
	 * @message S1_S2_TOGETHER
	 * @description ENG "«Параметр <S1> и <S2> должны быть заданы одновременно»" "«Параметр <S1> и <S2> должны быть заданы одновременно»"
	 * @description RUS "«Параметр <S1> и <S2> должны быть заданы одновременно»" "«Параметр <S1> и <S2> должны быть заданы одновременно»"
	 * @description RUM "«Параметр <S1> и <S2> должны быть заданы одновременно»" "«Параметр <S1> и <S2> должны быть заданы одновременно»"
	 */
	public static ServerMessage S1S2Toghetger() 
	{
		return getInstance().getMessage(S1_S2_TOGETHER);
	}
	
	/** @message VALUE_FOR_TYPE_NOT_FOUND
	 * @description ENG "«Значение %1 для типа характеристики %2 не найдено»" "«Значение %1 для типа характеристики %2 не найдено»"
	 * @description RUS "«Значение %1 для типа характеристики %2 не найдено»" "«Значение %1 для типа характеристики %2 не найдено»"
	 * @description RUM "«Значение %1 для типа характеристики %2 не найдено»" "«Значение %1 для типа характеристики %2 не найдено»"
	 */
	public static ServerMessage valueForTypeNotFound(String value, String type) 
	{
		MessageParameters params = new MessageParameters();
		params.addRawString(value);
		params.addRawString(type);
		return getInstance().getMessage(VALUE_FOR_TYPE_NOT_FOUND);		
	}
	
	
	/** @message COLL_EVT_TYP_NOT_FOUND
	 * @description ENG "«Тип события взыскания ДЗ %1 не найден»" "«Тип события взыскания ДЗ %1 не найден»"
	 * @description RUS "«Тип события взыскания ДЗ %1 не найден»" "«Тип события взыскания ДЗ %1 не найден»"
	 * @description RUM "«Тип события взыскания ДЗ %1 не найден»" "«Тип события взыскания ДЗ %1 не найден»"
	 */
	public static ServerMessage collEvtTypeNotFound(String type) 
	{
		MessageParameters params = new MessageParameters();
		params.addRawString(type);
		return getInstance().getMessage(COLL_EVT_TYP_NOT_FOUND, params);		
	}
	
	/** @message COLL_PROC_TM_NOT_FOUND
	 * @description ENG "«Шаблон процесса взыскания ДЗ %1 не найден»"  "«Шаблон процесса взыскания ДЗ %1 не найден»"
	 * @description RUS "«Шаблон процесса взыскания ДЗ %1 не найден»"  "«Шаблон процесса взыскания ДЗ %1 не найден»"
	 * @description RUM "«Шаблон процесса взыскания ДЗ %1 не найден»"  "«Шаблон процесса взыскания ДЗ %1 не найден»"
	 */ 
	public static ServerMessage collProcTmNotFound(String type) 
	{
		MessageParameters params = new MessageParameters();
		params.addRawString(type);
		return getInstance().getMessage(COLL_PROC_TM_NOT_FOUND, params);		
	}
	
	/** @message S5S6ORS7_IS_INVALID
	 * @description ENG "«Неверно задан параметр <S5, S6 или S7>»" "«Неверно задан параметр <S5, S6 или S7>»"
	 * @description RUS "«Неверно задан параметр <S5, S6 или S7>»" "«Неверно задан параметр <S5, S6 или S7>»"
	 * @description RUM "«Неверно задан параметр <S5, S6 или S7>»" "«Неверно задан параметр <S5, S6 или S7>»"
	 */
	public static ServerMessage s5S6OrS7IsInvalid() 
	{
		return getInstance().getMessage(S5S6ORS7_IS_INVALID);		
	}

}
