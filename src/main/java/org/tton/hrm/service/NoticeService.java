package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.Notice;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: NoticeService <br/>
 * Description: TODO <br/>
 * Date: 2018��1��18�� ����5:11:47 <br/>
 * <br/>
 * 
 * @author hanyouchuan(����)
 * 
 *         �޸ļ�¼
 * @version ��Ʒ�汾��Ϣ yyyy-mm-dd ����(����) �޸���Ϣ<br/>
 * 
 */

public interface NoticeService {
    /**
     * ������й���
     * 
     * @return Notice�����List����
     * */
    List<Notice> findNotice(Notice notice, PageModel pageModel);

    /**
     * ���id��ѯ����
     * 
     * @param id
     * @return �������
     * */
    Notice findNoticeById(Integer id);

    /**
     * ���idɾ���
     * 
     * @param id
     * */
    public void removeNoticeById(Integer id);

    /**
     * ��ӹ���
     * 
     * @param Notice
     *            �������
     * */
    void addNotice(Notice notice);

    /**
     * �޸Ĺ���
     * 
     * @param Notice
     *            �������
     * */
    void modifyNotice(Notice notice);
}
